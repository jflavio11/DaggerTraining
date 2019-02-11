package com.jflavio1.daggerexample.core.components.alerts;

import com.jflavio1.daggerexample.R;

/**
 * CustomAlertDialog
 *
 * @author Jose Flavio - jflavio90@gmail.com
 * @since 2/11/2019
 */
public class CustomAlertDialog extends BaseCustomAlertDialog {

    @Override
    int getDialogLayoutResId() {
        return R.layout.dialog_custom_full;
    }

    @Override
    int getDialogTitleTextViewResId() {
        return R.id.dialogCustomFull_tv_title;
    }

    @Override
    int getDialogImageViewResId() {
        return 0;
    }

    @Override
    int getDialogDescriptionTextViewResId() {
        return R.id.dialogCustomFull_tv_desc;
    }

    @Override
    int getAcceptButtonResId() {
        return R.id.dialogCustomFull_btn;
    }

}
