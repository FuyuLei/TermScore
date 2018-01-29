package comn.example.user.termscore.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import comn.example.user.termscore.R;
import comn.example.user.termscore.model.Score;

public class ScoreAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<Score> list;

    public ScoreAdapter(Context context, List<Score> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_rv_score, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
            ViewHolder h = (ViewHolder) holder;
            Score item = list.get(position);
            h.termYear.setText(item.getYearTerm());
            h.name.setText(item.getName());
            h.score.setText(item.getScore());
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private class ViewHolder extends RecyclerView.ViewHolder {
        private TextView termYear;
        private TextView name;
        private TextView score;

        ViewHolder(View v) {
            super(v);
            termYear = (TextView) v.findViewById(R.id.tv_item_rv_term_year);
            name = (TextView) v.findViewById(R.id.tv_item_rv_name);
            score = (TextView) v.findViewById(R.id.tv_item_rv_score);
        }
    }
}
