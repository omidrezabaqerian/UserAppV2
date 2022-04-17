package ir.omidrezabagherian.userapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import ir.omidrezabagherian.userapp.R
import ir.omidrezabagherian.userapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var bindingMain: ActivityMainBinding
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bindingMain = ActivityMainBinding.inflate(layoutInflater)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerViewMain) as NavHostFragment

        navController = navHostFragment.navController

        bindingMain.bottomNavigationViewMain.setOnItemSelectedListener { result ->
            when (result.itemId) {
                R.id.profile_menu -> {
                    navController.popBackStack()
                    navController.navigate(R.id.userCreateFragment)
                }
                R.id.search_menu -> {
                    navController.popBackStack()
                    navController.navigate(R.id.searchFragment)
                }
            }
            true
        }

        setContentView(bindingMain.root)
    }
}