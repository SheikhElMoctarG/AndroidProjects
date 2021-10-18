package Controller;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sheikh.allnews.DetailsActivity;
import com.sheikh.allnews.R;
import com.squareup.picasso.Picasso;

import java.util.List;
import Libraries.TalkText;
import Model.News;

public class Adepter extends RecyclerView.Adapter<Adepter.PostView> {


    private List<News> newsList;
    Context context;

    public Adepter(Context context, List<News> newsList) {
        this.newsList = newsList;
        this.context = context;
    }

    @NonNull
    @Override
    public Adepter.PostView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.plan_post_view, parent,false);
        return new PostView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostView holder, int position) {
        News news = newsList.get(position);
        Picasso.get().load(news.getImage()).resize(150,150).error(R.drawable.nophoto).into(holder.image);
            holder.title.setText(news.getTitle());
            holder.description.setText(news.getDescription());
            holder.date.setText(timeAgo.getTimeAgo(news.getDate(),context));
            holder.nameSource.setText(news.getNameSource());
            holder.text = news.getTitle();
            holder.link = news.getLink();
            Picasso.get().load(iconSite(news.getLink())).into(holder.iconSource);

    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public class PostView extends RecyclerView.ViewHolder {
        TextView title,description,date, nameSource;
        ImageView image, iconSource;
        String link;
        String text;
        public PostView(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);
            date = itemView.findViewById(R.id.date);
            image = itemView.findViewById(R.id.image);
            nameSource = itemView.findViewById(R.id.nameSource);
            iconSource = itemView.findViewById(R.id.iconSource);
            LinearLayout speechButton = itemView.findViewById(R.id.speechButton);
            TalkText talkText = new TalkText();
            talkText.prepare(context);
            speechButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("talk", "talk: "+text);
                    talkText.talk(text);
                }
            });
            clickText(title,description,nameSource);
            clickImage(image);
        }
        void click(){
            Intent intent = new Intent(context, DetailsActivity.class);
            intent.putExtra("title",title.getText().toString());
            intent.putExtra("ago",date.getText().toString());
            intent.putExtra("source",nameSource.getText().toString());
            intent.putExtra("link", link);
            context.startActivity(intent);
        }
        void clickText(TextView textView1, TextView textView2, TextView textView3){
            textView1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    click();
                }
            });
            textView2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    click();
                }
            });
            textView3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    click();
                }
            });
        }
        void clickImage(ImageView image){
            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    click();
                }
            });
        }

    }

    int iconSite(String link){
        if (link.startsWith("https://www.saharamedias.net/")){
            return R.drawable.saharamedias_icon;
        } else if (link.startsWith("https://alakhbar.info/")){
            return R.drawable.icon_lite;
        } else if (link.startsWith("https://www.bellewarmedia.com/")){
            return R.drawable.bellewarmedia;
        } else if (link.startsWith("http://tvm.mr/")){
            return R.drawable.tvm;
        } else if (link.startsWith("https://mourassiloun.com/")){
            return R.drawable.mourassiloun;
        } else if (link.startsWith("http://kiffainfo.net/")){
            return R.drawable.icon_lite;
        }  else if (link.startsWith("https://essahraa.net")){
            return R.drawable.essahraaplus;
        }else if (link.startsWith("https://www.zahraainfo.com/")){
            return R.drawable.mourassiloun;
        } else {
            return R.drawable.icon_lite;
        }
    }
    Boolean allowSites(String link){
        if (link.startsWith("https://alakhbar")){
            return false;
        } else {
            return true;
        }
    }


}
