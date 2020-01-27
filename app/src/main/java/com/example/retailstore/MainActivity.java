package com.example.retailstore;

import android.os.Bundle;

import com.example.retailstore.database.data.Product;
import com.example.retailstore.ui.cart.CartFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
{

    private AppBarConfiguration mAppBarConfiguration;
    private NavController mNavController;
    private DrawerLayout mDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        mNavController = Navigation.findNavController(this, R.id.nav_host_fragment);
        mDrawer = findViewById(R.id.drawer_layout);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_products_list,R.id.nav_cart)
                .setDrawerLayout(mDrawer)
                .build();

        setupNavigation();

        if (savedInstanceState == null)
        {
            //load product list
        }
        else
        {
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp()
    {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem)
    {
        switch (menuItem.getItemId()) {
            case R.id.nav_products_list: {
                mNavController.navigate(R.id.nav_products_list);
                break;
            }
            case R.id.nav_cart: {
                mNavController.navigate(R.id.nav_cart);
                break;
            }

        }
        mDrawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setupNavigation()
    {
        NavigationView navigationView = findViewById(R.id.nav_view);
        NavigationUI.setupActionBarWithNavController(this, mNavController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, mNavController);
        navigationView.setNavigationItemSelectedListener(this);

//        mNavController.addOnDestinationChangedListener { _, destination, _ ->
//            if (destination.id in arrayOf(
//                    R.id.createLetterFragment,
//                    R.id.presentationFragment,
//                    R.id.editProfileFragment
//        )
//      ) {
//                fab.hide()
//            } else {
//                fab.show()
//            }
//
//            if (destination.id == R.id.presentationFragment) {
//                toolbar.visibility = View.GONE
//            } else {
//                toolbar.visibility = View.VISIBLE
//            }
//        }

    }
}
