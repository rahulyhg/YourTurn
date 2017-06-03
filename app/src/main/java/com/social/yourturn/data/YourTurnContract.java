package com.social.yourturn.data;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;
import android.text.format.Time;

/**
 * Created by ousma on 5/3/2017.
 */

public class YourTurnContract {

    public static final String CONTENT_AUTHORITY = "com.social.yourturn";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_MEMBER = "member";
    public static final String PATH_USER = "user";
    public static final String PATH_GROUP = "group";
    public static final String PATH_LEDGER = "ledger";

    public static long normalizeDate(long startDate) {
        // normalize the start date to the beginning of the (UTC) day
        Time time = new Time();
        time.set(startDate);
        int julianDay = Time.getJulianDay(startDate, time.gmtoff);
        return time.setJulianDay(julianDay);
    }

    public static final class MemberEntry implements BaseColumns {

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_MEMBER).build();
        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_MEMBER;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_MEMBER;

        public static final String TABLE_NAME = "members";
        public static final String COLUMN_MEMBER_NAME = "display_name";
        public static final String COLUMN_MEMBER_PHONE_NUMBER = "data1";
        public static final String COLUMN_MEMBER_REGISTERED = "is_registered";
        public static final String COLUMN_MEMBER_THUMBNAIL = "thumbnail";
        public static final String COLUMN_MEMBER_SORT_KEY_PRIMARY = "sort_key";
        public static final String COLUMN_MEMBER_LOOKUP_KEY = "lookup";
        public static final String COLUMN_MEMBER_CREATED_DATE = "created_date";
        public static final String COLUMN_MEMBER_UPDATED_DATE = "updated_date";

        public static Uri buildMemberUri(long id){
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }

    public static final class UserEntry implements BaseColumns {

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_USER).build();
        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_USER;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_USER;

        public static final String TABLE_NAME = "users";
        public static final String COLUMN_USER_NAME = "username";
        public static final String COLUMN_USER_ID = "contact_id";
        public static final String COLUMN_USER_DEVICE_ID = "device_id";
        public static final String COLUMN_USER_PHONE_NUMBER = "phone_number";
        public static final String COLUMN_USER_PASSWORD = "password";
        public static final String COLUMN_USER_THUMBNAIL = "thumbnail";
        public static final String COLUMN_USER_CREATED_DATE = "created_date";
        public static final String COLUMN_USER_UPDATED_DATE = "updated_date";

        public static Uri buildUserUri(long id){
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }

    public static final class GroupEntry implements BaseColumns {

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_GROUP).build();

        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_GROUP;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_GROUP;

        public static final String TABLE_NAME = "groups";

        public static final String COLUMN_GROUP_ID = "group_id";
        public static final String COLUMN_USER_KEY = "usr_contact_id";
        public static final String COLUMN_GROUP_NAME = "group_name";
        public static final String COLUMN_GROUP_THUMBNAIL = "group_thumbnail";
        public static final String COLUMN_GROUP_CREATED_DATE = "created_date";
        public static final String COLUMN_GROUP_UPDATED_DATE = "updated_date";
        public static final String COLUMN_GROUP_CREATOR = "group_creator";

        public static Uri buildGroupUri(long id){
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }

    public static final class LedgerEntry implements BaseColumns {

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_LEDGER).build();

        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_LEDGER;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_LEDGER;

        public static final String TABLE_NAME = "ledger";

        public static final String COLUMN_GROUP_KEY = "group_id";
        public static final String COLUMN_USER_KEY = "usr_contact_id";
        public static final String COLUMN_USER_SHARE = "usr_share";
        public static final String COLUMN_TOTAL_AMOUNT = "total_amount";
        public static final String COLUMN_GROUP_CREATED_DATE = "created_date";
        public static final String COLUMN_GROUP_UPDATED_DATE = "updated_date";

        public static Uri buildLedgerUri(long id){
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }
}
