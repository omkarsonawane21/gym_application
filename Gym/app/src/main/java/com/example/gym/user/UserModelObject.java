package com.example.gym.user;

import com.example.gym.R;

public enum UserModelObject {

    UPDATE_USERPROFILE(R.string.update_userprofile, R.layout.updateuserprofile);
    private int mTitleResId;
    private int mLayoutResId;

    UserModelObject(int titleResId, int layoutResId) {
        mTitleResId = titleResId;
        mLayoutResId = layoutResId;
    }

    public int getTitleResId() {
        return mTitleResId;
    }

    public int getLayoutResId() {
        return mLayoutResId;
    }
}
