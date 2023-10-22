package es.upm.miw.bantumi;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDialogFragment;

public class RestartAlertDialog extends AppCompatDialogFragment {
    interface Back {
        void onSuccess();
    }

    int title;
    int message;
    Back back;

    public RestartAlertDialog(int title, int message, Back back) {
        this.title = title;
        this.message = message;
        this.back = back;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final MainActivity main = (MainActivity) getActivity();
        assert main != null;
        AlertDialog.Builder builder = new AlertDialog.Builder(main);
        builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton(
                        getString(R.string.dialogoSí),
                        (dialog, which) -> {
                            back.onSuccess();
                        }
                )
                .setNegativeButton(
                        getString(R.string.dialogoNo),
                        (dialog, which) -> {}
                );

        // Create the AlertDialog object and return it
        return builder.create();
    }
}
