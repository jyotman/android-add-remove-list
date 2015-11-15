package yet.best.addremovelist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    final int LIST_RESULT = 100;

    ArrayList<String> list;
    RecyclerView recyclerView;
    ListAdapter listAdapter;
    LinearLayoutManager llm;
    Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        list = getIntent().getStringArrayListExtra("list");
        //To show at least one row
        if(list == null || list.size() == 0) {
            list = new ArrayList<>();
            list.add("");
        }

        submitButton = (Button) findViewById(R.id.submit_button);
        recyclerView = (RecyclerView) findViewById(R.id.rv);
        listAdapter = new ListAdapter(list, this);
        llm = new LinearLayoutManager(this);

        //Setting the adapter
        recyclerView.setAdapter(listAdapter);
        recyclerView.setLayoutManager(llm);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                list = listAdapter.getStepList();
                i.putStringArrayListExtra("list", list);
                setResult(LIST_RESULT, i);
                finish();
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
