package com.social.yourturn.adapters;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import com.social.yourturn.R;
import com.social.yourturn.data.YourTurnContract;
import com.social.yourturn.models.Contact;
import com.social.yourturn.utils.ImageLoader;

import org.apache.commons.lang3.text.WordUtils;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * Created by ousma on 4/18/2017.
 */

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder>{

    private final static String TAG = CustomAdapter.class.getSimpleName();
    private Context mContext;
    private ArrayList<Contact> mContactList;
    private ImageLoader imageLoader;

    public CustomAdapter(Context context, ArrayList<Contact> cursorList){
        mContext = context;
        mContactList = cursorList;
        imageLoader = new ImageLoader(mContext);
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(mContext).inflate(R.layout.group_item_layout, null);
        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        Contact contact = mContactList.get(position);
        final String displayName = contact.getName();

        Cursor thumbnailCursor = mContext.getContentResolver().query(YourTurnContract.MemberEntry.CONTENT_URI,
                new String[]{YourTurnContract.MemberEntry.COLUMN_MEMBER_THUMBNAIL},
                YourTurnContract.MemberEntry.COLUMN_MEMBER_PHONE_NUMBER + "=" + DatabaseUtils.sqlEscapeString(contact.getPhoneNumber()), null, null);
        thumbnailCursor.moveToNext();
        String thumbnail = thumbnailCursor.getString(thumbnailCursor.getColumnIndex(YourTurnContract.MemberEntry.COLUMN_MEMBER_THUMBNAIL));

        imageLoader.DisplayImage(thumbnail, holder.thumbnailView);

        if(displayName != null) {
            holder.usernameView.setText(WordUtils.capitalize(displayName.toLowerCase(), null));
        }
    }

    @Override
    public int getItemCount() {
        return mContactList.size();
    }

    public static class CustomViewHolder extends RecyclerView.ViewHolder {
        public CircleImageView thumbnailView;
        public TextView usernameView;

        public CustomViewHolder(View itemView) {
            super(itemView);
            Log.d(TAG, String.valueOf(itemView.getX()));
            Log.d(TAG, String.valueOf(itemView.getY()));
            this.thumbnailView = (CircleImageView) itemView.findViewById(R.id.thumbnail);
            this.usernameView = (TextView) itemView.findViewById(R.id.username);
        }
    }
}
