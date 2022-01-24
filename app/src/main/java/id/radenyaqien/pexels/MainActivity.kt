package id.radenyaqien.pexels

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import id.radenyaqien.pexels.ui.ListViewModel
import id.radenyaqien.core.ui.MyAdapter
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viemodel: ListViewModel by viewModels()
    private lateinit var text : RecyclerView
    private val mAdapter by lazy {
        MyAdapter()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        text = findViewById(R.id.recycler_view)

        text.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = mAdapter
        }
        lifecycleScope.launchWhenStarted {
            //repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viemodel.dat().collect {
                    //it.pagin?.let {dat ->
                        mAdapter.submitData(it)
                    //}
                }
            //}

        }
    }


}