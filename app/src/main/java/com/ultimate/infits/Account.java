package com.ultimate.infits;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Account#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Account extends Fragment {
    DataFromDatabase dataFromDatabase;
    ImageView male, female,profile_pic;
    Button logout,save;
    String dietitian_acc_gender="";
    String dieititian_acc_name, dietitiamn_acc_age, dietitian_acc_email,dietitian_acc_phoneno;

    private Bitmap bitmap;
    private File destination = null;
    private InputStream inputStreamImg;
    private String imgPath = null;
    private final int PICK_IMAGE_CAMERA = 1, PICK_IMAGE_GALLERY = 2;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Account() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Account.
     */
    // TODO: Rename and change types and number of parameters
    public static Account newInstance(String param1, String param2) {
        Account fragment = new Account();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_account, container, false);
        male = view.findViewById(R.id.gender_male_icon);
        female=view.findViewById(R.id.gender_female_icon);
        EditText name=view.findViewById(R.id.name_edt);
        name.setText(dataFromDatabase.name);
        EditText age=view.findViewById(R.id.age_edt);
        age.setText(dataFromDatabase.age);
        EditText email=view.findViewById(R.id.email_edt);
        email.setText(dataFromDatabase.email);
        EditText phone=view.findViewById(R.id.phone_edt);
        phone.setText(dataFromDatabase.mobile);
        profile_pic=view.findViewById(R.id.dp);
        ImageView select_pic= view.findViewById(R.id.select_dp);
        save=view.findViewById(R.id.button_save);
        logout=view.findViewById(R.id.button_logout);

        if(dataFromDatabase.gender=="M"){
            male.setImageResource(R.drawable.gender_male_selected);
            female.setImageResource(R.drawable.gender_female);
        }else if(dataFromDatabase.gender=="F"){
            male.setImageResource(R.drawable.gender_male);
            female.setImageResource(R.drawable.gender_female_selected);
        }
        select_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });

        male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                male.setImageResource(R.drawable.gender_male_selected);
                female.setImageResource(R.drawable.gender_female);
                dietitian_acc_gender="M";
            }
        });
        female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                male.setImageResource(R.drawable.gender_male);
                female.setImageResource(R.drawable.gender_female_selected);
                dietitian_acc_gender="F";
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getActivity(),LoginScreen.class);
                startActivity(i);
                getActivity().finish();
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //save dietitian_acc_variables into db
            }
        });
        return view;
    }


    private void selectImage() {
        try {
            PackageManager pm = getContext().getPackageManager();
            int hasPerm = pm.checkPermission(Manifest.permission.CAMERA, getContext().getPackageName());
            if (hasPerm == PackageManager.PERMISSION_GRANTED) {
                //final CharSequence[] options = {"Take Photo", "Choose From Gallery","Remove picture","Cancel"};
                final CharSequence[] options = { "Choose From Gallery","Remove picture","Cancel"};
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Select Option");
                builder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                      /*  if (options[item].equals("Take Photo")) {
                            dialog.cancel();
                            Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(intent, PICK_IMAGE_CAMERA);
                        } else*/
                        if (options[item].equals("Choose From Gallery")) {
                            dialog.cancel();
                            Intent pickPhoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            startActivityForResult(pickPhoto, PICK_IMAGE_GALLERY);
                        } else if (options[item].equals("Cancel")) {
                            dialog.cancel();
                        }
                        else if(options[item].equals("Remove picture")){
                            dialog.dismiss();
                            profile_pic.setImageResource(R.drawable.blankdp);
                        }
                        else
                            dialog.cancel();
                    }
                });
                builder.show();
            } else {
                Toast.makeText(getActivity(), "Camera Permission error", Toast.LENGTH_SHORT).show();
                Toast.makeText(getActivity(), "Change app permission in your device settings", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(getActivity(), "Camera Permission error", Toast.LENGTH_SHORT).show();
            Toast.makeText(getActivity(), "Change app permission in your device settings", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }
        @Override
        public void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            inputStreamImg = null;
           /* if (requestCode == PICK_IMAGE_CAMERA) {
                try {
                    Log.w("error","entered");
                    if((data.getData()) != null) {
                        Log.w("error","!1!");
                        Uri selectedImage = data.getData();
                        Log.w("Error",selectedImage.toString());
                        profile_pic.setImageURI(selectedImage);
                       //bitmap = (Bitmap) data.getExtras().get("data");
                       // ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                        //bitmap.compress(Bitmap.CompressFormat.JPEG, 50, bytes);
                        //profile_pic.setImageBitmap(bitmap);
                       // String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
                        //destination = new File(Environment.getExternalStorageDirectory() + "/" +getString(R.string.app_name), "IMG_" + timeStamp + ".jpg");
                        //FileOutputStream fo;
                    /*try {
                        destination.createNewFile();
                        fo = new FileOutputStream(destination);
                        fo.write(bytes.toByteArray());
                        fo.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }*

                    imgPath = destination.getAbsolutePath();
                  //  profile_pic.setImageBitmap(selectedImage);
                    }

                } catch (Exception e) {
                    Toast.makeText(getActivity(),"No picture was clicked",Toast.LENGTH_SHORT).show();
                }
            } */
            //else
            if (requestCode == PICK_IMAGE_GALLERY) {
                     try {
                         if( data.getData() != null) {
                             Uri selectedImage = data.getData();
                             bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), selectedImage);
                             //ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                            // bitmap.compress(Bitmap.CompressFormat.JPEG, 50, bytes);
                             profile_pic.setImageBitmap(bitmap);
                  /*  imgPath = getRealPathFromURI(selectedImage);
                    destination = new File(imgPath.toString());*/

                         }
                    } catch (Exception e) {
                        Toast.makeText(getActivity(),"No picture selected",Toast.LENGTH_SHORT).show();
                     }

            }
        }

        public String getRealPathFromURI(Uri contentUri) {
            String[] proj = {MediaStore.Audio.Media.DATA};
            Cursor cursor = getActivity().managedQuery(contentUri, proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        }
}