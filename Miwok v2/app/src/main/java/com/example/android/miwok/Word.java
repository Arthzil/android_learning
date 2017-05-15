package com.example.android.miwok;

public class Word {
    private String mMiwokTranslation;
    private String mDefaultTranslation;
    private int mIconId = NO_IMAGE_PROVIDED;
    private int mSoundId;

    private static final int NO_IMAGE_PROVIDED = -1;

    public Word (String defaultTranslation, String miwokTranslation, int iconId, int soundId) {
        mMiwokTranslation = miwokTranslation;
        mDefaultTranslation = defaultTranslation;
        mIconId = iconId;
        mSoundId = soundId;
    }

    public Word (String defaultTranslation, String miwokTranslation, int soundId) {
        mMiwokTranslation = miwokTranslation;
        mDefaultTranslation = defaultTranslation;
        mSoundId = soundId;
    }

    public String getMiwokTranslation() {
        return mMiwokTranslation;
    }

    public String getDefaultTranslation() {
        return mDefaultTranslation;
    }

    public int getIconId() {
        return mIconId;
    }

    public boolean hasImage() {
        return mIconId != NO_IMAGE_PROVIDED;
    }

    public int getSoundId() {return  mSoundId;}
}
