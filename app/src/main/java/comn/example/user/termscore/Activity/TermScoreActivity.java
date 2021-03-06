package comn.example.user.termscore.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

import comn.example.user.termscore.Adapter.ScoreAdapter;
import comn.example.user.termscore.R;
import comn.example.user.termscore.model.Score;
import comn.example.user.termscore.retrofit.TermScoreService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TermScoreActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtYear;
    private EditText edtTerm;
    private EditText edtStuid;
    private WebView webView;
    private ArrayList<Score> list;
    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_score);

        edtYear = (EditText) findViewById(R.id.edt_term_score_years);
        edtTerm = (EditText) findViewById(R.id.edt_term_score_term);
        edtStuid = (EditText) findViewById(R.id.edt_term_score_std_id);
        rv = (RecyclerView) findViewById(R.id.rv_main);
//        webView = (WebView) findViewById(R.id.wv_term_score);

//        webView.getSettings().setJavaScriptEnabled(true);
        list = new ArrayList<>();
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new ScoreAdapter(this, list));
        findViewById(R.id.btn_term_score_search).setOnClickListener(this);
    }

    private void udpateAdapter() {
        rv.getAdapter().notifyDataSetChanged();
    }

    private void parser(String html) {
        list.clear();
        Document doc = Jsoup.parse(html);
        Elements courses = doc.select("li.Midli");
        for (Element course : courses) {
            Elements items = course.select("p");
            if (items.size() == 3) {
                Score grade = new Score();
                grade.setYearTerm(items.get(0).text());
                grade.setName(items.get(1).text());
                grade.setScore(items.get(2).text());
                list.add(grade);
            }
        }
        udpateAdapter();
    }

    @Override
    public void onClick(View v) {
        String year = edtYear.getText().toString();
        String term = edtTerm.getText().toString();
        String stuNo = edtStuid.getText().toString();
        getTermScore(year, term, stuNo, "1080", "歷年成績細項科目檔");
    }

    private void getTermScore(String year, String term, String stuNo, String width, String flag){
        TermScoreService service = TermScoreService.retrofit.create(TermScoreService.class);
        Call<String> call = service.Data(year, term, stuNo, width, flag);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                parser(response.body());
//                webView.loadData(data,"text/html","UTF-8");
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d("test",t.toString());
            }
        });
    }
}
