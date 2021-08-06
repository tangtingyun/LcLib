package com.step.lclib.work.utils;

import java.util.ArrayList;
import java.util.Locale;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.text.TextUtils;


public class SaveUriUtils {

    Activity activity;

    public SaveUriUtils(Activity activity) {
        this.activity = activity;
    }


    public static void testIntent(Activity activity) {
        // Creates a new Intent to insert a contact
        Intent intent = new Intent(ContactsContract.Intents.Insert.ACTION);
        // Sets the MIME type to match the Contacts Provider
        intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);

        intent.putExtra(
                ContactsContract.Intents.Insert.NAME,
                "工作账号"
        );
        // Inserts a phone number
        intent.putExtra(ContactsContract.Intents.Insert.PHONE, "455-123455");
        /*
         * In this example, sets the phone type to be a work phone.
         * You can set other phone types as necessary.
         */
        intent.putExtra(
                ContactsContract.Intents.Insert.PHONE_TYPE,
                ContactsContract.CommonDataKinds.Phone.TYPE_WORK
        );
        // Sends the Intent
        activity.startActivity(intent);
    }

    // https://gist.github.com/Rflyee/7582926
    public static boolean compareHasPhoneNum(Context context, String user_phone) {
        if (TextUtils.isEmpty(user_phone)) {
            return true;
        }
        boolean hasPhoneNum = false;
        ContentResolver resolver = context.getContentResolver();
        Cursor phoneCursor = resolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                new String[]{ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                        ContactsContract.CommonDataKinds.Phone.NUMBER, ContactsContract.CommonDataKinds.Photo.PHOTO_ID,
                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID}
                , null, null, null);
        if (phoneCursor != null) {
            while (phoneCursor.moveToNext()) {
                String phoneNumber = phoneCursor.getString(1);
                if (TextUtils.isEmpty(phoneNumber))
                    continue;
                if (phoneNumber.contains(user_phone)) {
                    hasPhoneNum = true;
                    break;
                }
//                bean.name = phoneCursor.getString(0);
//                bean.id = phoneCursor.getLong(2);
//                bean.iconId = phoneCursor.getLong(3);
            }
            phoneCursor.close();
        }
        return hasPhoneNum;
    }


    public static void addContact(Context context, String name, String telephone) {
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(telephone)) {
            return;
        }
        ContentResolver resolver = context.getContentResolver();
        ArrayList<ContentProviderOperation> operations = new ArrayList<ContentProviderOperation>();
        // 下面的操作会根据表中已有的id使用情况自动生成新联系人的一行
        ContentProviderOperation op1 = ContentProviderOperation
                .newInsert(ContactsContract.RawContacts.CONTENT_URI)
                .withValue(ContactsContract.RawContacts.ACCOUNT_NAME, null)
                .withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, null)
                .build();
        operations.add(op1);
        // 添加联系人
        ContentProviderOperation op2 = ContentProviderOperation
                .newInsert(ContactsContract.Data.CONTENT_URI)
                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                .withValue(
                        ContactsContract.Data.MIMETYPE,
                        ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
                .withValue(
                        ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME,
                        name).build();
        operations.add(op2);
        // 添加联系电话
        ContentProviderOperation op3 = ContentProviderOperation
                .newInsert(ContactsContract.Data.CONTENT_URI)
                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                .withValue(
                        ContactsContract.Data.MIMETYPE,
                        ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
                .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER,
                        telephone)
                .withValue(ContactsContract.CommonDataKinds.Phone.TYPE,
                        ContactsContract.CommonDataKinds.Phone.TYPE_WORK)
                .build();
        operations.add(op3);
        try {
            resolver.applyBatch(ContactsContract.AUTHORITY, operations);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static final String[] EMAIL_TYPE_STRINGS = {"home", "work", "mobile"};
    private static final String[] PHONE_TYPE_STRINGS = {"home", "work", "mobile", "fax", "pager", "main"};
    private static final String[] ADDRESS_TYPE_STRINGS = {"home", "work"};
    private static final int[] EMAIL_TYPE_VALUES = {
            ContactsContract.CommonDataKinds.Email.TYPE_HOME,
            ContactsContract.CommonDataKinds.Email.TYPE_WORK,
            ContactsContract.CommonDataKinds.Email.TYPE_MOBILE,
    };
    private static final int[] PHONE_TYPE_VALUES = {
            ContactsContract.CommonDataKinds.Phone.TYPE_HOME,
            ContactsContract.CommonDataKinds.Phone.TYPE_WORK,
            ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE,
            ContactsContract.CommonDataKinds.Phone.TYPE_FAX_WORK,
            ContactsContract.CommonDataKinds.Phone.TYPE_PAGER,
            ContactsContract.CommonDataKinds.Phone.TYPE_MAIN,
    };
    private static final int[] ADDRESS_TYPE_VALUES = {
            ContactsContract.CommonDataKinds.StructuredPostal.TYPE_HOME,
            ContactsContract.CommonDataKinds.StructuredPostal.TYPE_WORK,
    };
    private static final int NO_TYPE = -1;

    /**
     * When using Type.CONTACT, these arrays provide the keys for adding or retrieving multiple
     * phone numbers and addresses.
     */
    public static final String[] PHONE_KEYS = {
            ContactsContract.Intents.Insert.PHONE,
            ContactsContract.Intents.Insert.SECONDARY_PHONE,
            ContactsContract.Intents.Insert.TERTIARY_PHONE
    };

    public static final String[] PHONE_TYPE_KEYS = {
            ContactsContract.Intents.Insert.PHONE_TYPE,
            ContactsContract.Intents.Insert.SECONDARY_PHONE_TYPE,
            ContactsContract.Intents.Insert.TERTIARY_PHONE_TYPE
    };

    public static final String[] EMAIL_KEYS = {
            ContactsContract.Intents.Insert.EMAIL,
            ContactsContract.Intents.Insert.SECONDARY_EMAIL,
            ContactsContract.Intents.Insert.TERTIARY_EMAIL
    };

    public static final String[] EMAIL_TYPE_KEYS = {
            ContactsContract.Intents.Insert.EMAIL_TYPE,
            ContactsContract.Intents.Insert.SECONDARY_EMAIL_TYPE,
            ContactsContract.Intents.Insert.TERTIARY_EMAIL_TYPE
    };


    public final void addPhoneOnlyContact(String[] names, String[] phoneNumbers, String[] phoneTypes) {
        addContact(names, null, null, phoneNumbers, phoneTypes, null, null, null, null, null, null, null, null, null, null, null);
    }

    public final void addEmailOnlyContact(String[] emails, String[] emailTypes) {
        addContact(null, null, null, null, null, emails, emailTypes, null, null, null, null, null, null, null, null, null);
    }

    public final void addContact(String[] names,
                                 String[] nicknames,
                                 String pronunciation,
                                 String[] phoneNumbers,
                                 String[] phoneTypes,
                                 String[] emails,
                                 String[] emailTypes,
                                 String note,
                                 String instantMessenger,
                                 String address,
                                 String addressType,
                                 String org,
                                 String title,
                                 String[] urls,
                                 String birthday,
                                 String[] geo) {

        // Only use the first name in the array, if present.
        Intent intent = new Intent(Intent.ACTION_INSERT_OR_EDIT, ContactsContract.Contacts.CONTENT_URI);
        intent.setType(ContactsContract.Contacts.CONTENT_ITEM_TYPE);
        putExtra(intent, ContactsContract.Intents.Insert.NAME, names != null && names.length > 0 ? names[0] : null);

        putExtra(intent, ContactsContract.Intents.Insert.PHONETIC_NAME, pronunciation);

        if (phoneNumbers != null) {
            int phoneCount = Math.min(phoneNumbers.length, PHONE_KEYS.length);
            for (int x = 0; x < phoneCount; x++) {
                putExtra(intent, PHONE_KEYS[x], phoneNumbers[x]);
                if (phoneTypes != null && x < phoneTypes.length) {
                    int type = toPhoneContractType(phoneTypes[x]);
                    if (type >= 0) {
                        intent.putExtra(PHONE_TYPE_KEYS[x], type);
                    }
                }
            }
        }

        if (emails != null) {
            int emailCount = Math.min(emails.length, EMAIL_KEYS.length);
            for (int x = 0; x < emailCount; x++) {
                putExtra(intent, EMAIL_KEYS[x], emails[x]);
                if (emailTypes != null && x < emailTypes.length) {
                    int type = toEmailContractType(emailTypes[x]);
                    if (type >= 0) {
                        intent.putExtra(EMAIL_TYPE_KEYS[x], type);
                    }
                }
            }
        }

        ArrayList<ContentValues> data = new ArrayList<>();
        if (urls != null) {
            for (String url : urls) {
                if (url != null && !url.isEmpty()) {
                    ContentValues row = new ContentValues(2);
                    row.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Website.CONTENT_ITEM_TYPE);
                    row.put(ContactsContract.CommonDataKinds.Website.URL, url);
                    data.add(row);
                    break;
                }
            }
        }

        if (birthday != null) {
            ContentValues row = new ContentValues(3);
            row.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Event.CONTENT_ITEM_TYPE);
            row.put(ContactsContract.CommonDataKinds.Event.TYPE, ContactsContract.CommonDataKinds.Event.TYPE_BIRTHDAY);
            row.put(ContactsContract.CommonDataKinds.Event.START_DATE, birthday);
            data.add(row);
        }

        if (nicknames != null) {
            for (String nickname : nicknames) {
                if (nickname != null && !nickname.isEmpty()) {
                    ContentValues row = new ContentValues(3);
                    row.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Nickname.CONTENT_ITEM_TYPE);
                    row.put(ContactsContract.CommonDataKinds.Nickname.TYPE,
                            ContactsContract.CommonDataKinds.Nickname.TYPE_DEFAULT);
                    row.put(ContactsContract.CommonDataKinds.Nickname.NAME, nickname);
                    data.add(row);
                    break;
                }
            }
        }

        if (!data.isEmpty()) {
            intent.putParcelableArrayListExtra(ContactsContract.Intents.Insert.DATA, data);
        }

        StringBuilder aggregatedNotes = new StringBuilder();
        if (note != null) {
            aggregatedNotes.append('\n').append(note);
        }
        if (geo != null && geo.length >= 2) {
            aggregatedNotes.append('\n').append(geo[0]).append(',').append(geo[1]);
        }

        if (aggregatedNotes.length() > 0) {
            // Remove extra leading '\n'
            putExtra(intent, ContactsContract.Intents.Insert.NOTES, aggregatedNotes.substring(1));
        }

        if (instantMessenger != null && instantMessenger.startsWith("xmpp:")) {
            intent.putExtra(ContactsContract.Intents.Insert.IM_PROTOCOL, ContactsContract.CommonDataKinds.Im.PROTOCOL_JABBER);
            intent.putExtra(ContactsContract.Intents.Insert.IM_HANDLE, instantMessenger.substring(5));
        } else {
            putExtra(intent, ContactsContract.Intents.Insert.IM_HANDLE, instantMessenger);
        }

        putExtra(intent, ContactsContract.Intents.Insert.POSTAL, address);
        if (addressType != null) {
            int type = toAddressContractType(addressType);
            if (type >= 0) {
                intent.putExtra(ContactsContract.Intents.Insert.POSTAL_TYPE, type);
            }
        }
        putExtra(intent, ContactsContract.Intents.Insert.COMPANY, org);
        putExtra(intent, ContactsContract.Intents.Insert.JOB_TITLE, title);
        launchIntent(intent);
    }

    private static int toEmailContractType(String typeString) {
        return doToContractType(typeString, EMAIL_TYPE_STRINGS, EMAIL_TYPE_VALUES);
    }

    private static int toPhoneContractType(String typeString) {
        return doToContractType(typeString, PHONE_TYPE_STRINGS, PHONE_TYPE_VALUES);
    }

    private static int toAddressContractType(String typeString) {
        return doToContractType(typeString, ADDRESS_TYPE_STRINGS, ADDRESS_TYPE_VALUES);
    }

    private static int doToContractType(String typeString, String[] types, int[] values) {
        if (typeString == null) {
            return NO_TYPE;
        }
        for (int i = 0; i < types.length; i++) {
            String type = types[i];
            if (typeString.startsWith(type) || typeString.startsWith(type.toUpperCase(Locale.ENGLISH))) {
                return values[i];
            }
        }
        return NO_TYPE;
    }


    private static void putExtra(Intent intent, String key, String value) {
        if (value != null && !value.isEmpty()) {
            intent.putExtra(key, value);
        }
    }


    final void launchIntent(Intent intent) {
        try {
            if (intent != null) {
                activity.startActivity(intent);
            }
        } catch (ActivityNotFoundException ignored) {

        }
    }

}
