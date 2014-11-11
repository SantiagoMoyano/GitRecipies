package gitrecipes.demolandia.com.gitrecipes;

/**
 * Created by root on 04/11/14.
 */
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.ImageButton;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class SimpleListDialog extends DialogFragment {
    public static int position=3;
    public static String name = Environment.getExternalStorageDirectory() + "/test.jpg";
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Que hacer:").setItems(
                R.array.options, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // The 'which' argument contains the index position
                        // of the selected item
                        position=which;
                        Intent intent =  new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        if(position == 0){
                            Uri output = Uri.fromFile(new File(name));
                            intent.putExtra(MediaStore.EXTRA_OUTPUT, output);
                        }else if (position==1){
                            intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);

                        }
                        startActivityForResult(intent, position);
                    }

                });

        return builder.create();
    }
}
