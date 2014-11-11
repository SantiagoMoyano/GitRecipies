package gitrecipes.demolandia.com.gitrecipes;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.app.Fragment;
import android.app.FragmentManager;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;


public class MainDrawer extends ActionBarActivity {
    private String[] misItems;
    private DrawerLayout miLayout;
    private ListView miList;
    private CharSequence miTitle;
    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_drawer);

        misItems = getResources().getStringArray(R.array.menues);
        miLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        miList = (ListView) findViewById(R.id.left_drawer);

        // Set the adapter for the list view
        miList.setAdapter(new ArrayAdapter<String>(this,
                R.layout.drawer_list_item, misItems));
        // Set the list's click listener
        miList.setOnItemClickListener(new DrawerItemClickListener());
        drawerToggle = new ActionBarDrawerToggle(this,
                miLayout,
                R.drawable.git_recipes_short,
                R.string.drawer_open,
                R.string.drawer_close) {

            public void onDrawerClosed(View view) {
                ActivityCompat.invalidateOptionsMenu(MainDrawer.this);
            }

            public void onDrawerOpened(View drawerView) {
                ActivityCompat.invalidateOptionsMenu(MainDrawer.this);
            }
        };

        miLayout.setDrawerListener(drawerToggle);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

     protected void onPostCreate(Bundle savedInstanceState) {
         super.onPostCreate(savedInstanceState);
         // Sync the toggle state after onRestoreInstanceState has occurred.
         drawerToggle.syncState();
     }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_drawer, menu);
        return true;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void crear(View view){

    }
    public void entrar(View view){
        showSimpleList(view);
        ImageButton boton= (ImageButton) findViewById(R.id.imagen);
        //startActivity(new Intent(getApplicationContext(), MainDrawer.class));

    }
     @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
         if (SimpleListDialog.position==1){
             Uri selectedImage = data.getData();
             InputStream is;
             try {
                 is = getContentResolver().openInputStream(selectedImage);
                 BufferedInputStream bis = new BufferedInputStream(is);
                 Bitmap bitmap = BitmapFactory.decodeStream(bis);
                 ImageButton boton= (ImageButton) findViewById(R.id.imagen);
                 Bitmap scaled = Bitmap.createScaledBitmap(bitmap, boton.getHeight(), boton.getWidth() , true);
                 boton.setImageBitmap(scaled);
             } catch (FileNotFoundException e) {}
         }else if(SimpleListDialog.position==0){
             ImageButton boton= (ImageButton) findViewById(R.id.imagen);
             Bitmap scaled = Bitmap.createScaledBitmap(BitmapFactory.decodeFile(SimpleListDialog.name), boton.getHeight(), boton.getWidth(), true);
             boton.setImageBitmap(scaled);
             new MediaScannerConnection.MediaScannerConnectionClient() {
                 private MediaScannerConnection msc = null; {
                     msc = new MediaScannerConnection(getApplicationContext(), this); msc.connect();
                 }
                 public void onMediaScannerConnected() {
                     msc.scanFile(SimpleListDialog.name, null);
                 }
                 public void onScanCompleted(String path, Uri uri) {
                     msc.disconnect();
                 }
             };
             /*if (data != null) {
                 if (data.hasExtra("data")) {
                     ImageButton boton= (ImageButton) findViewById(R.id.imagen);
                     boton.setImageBitmap((Bitmap) data.getParcelableExtra("data"));
                 }
             }*/
         }
     }


    public void showSimpleList(View view) {
        DialogFragment dialog = new SimpleListDialog();
        dialog.show(getSupportFragmentManager(), "dialog");

    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id) {
            selectItem(position);
        }
    }
    private void selectItem(int position) {
        Fragment fragment = new MenuFragment();
        Bundle args = new Bundle();
        args.putInt(MenuFragment.ARG_MENU_NUMBER, position);
        fragment.setArguments(args);

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.content_frame, fragment)
                .commit();

        miList.setItemChecked(position, true);
        setTitle(misItems[position]);
        miLayout.closeDrawer(miList);
    }


    public static class MenuFragment extends Fragment {
        public static final String ARG_MENU_NUMBER = "menu_number";

        public MenuFragment(){

        }

        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState){

            View rootView;
            int position = getArguments().getInt(ARG_MENU_NUMBER);
            switch (position){
                case 0:
                    rootView = inflater.inflate(R.layout.crear_recetas, container, false);
                    break;
                case 1:
                    rootView = inflater.inflate(R.layout.mis_recetas, container, false);
                    break;
                default:
                    rootView = inflater.inflate(R.layout.mi_perfil, container, false);
                    break;
            }
            return rootView;
        }
    }
}
