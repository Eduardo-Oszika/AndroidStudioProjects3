package com.oszika.provapdm2v2.util.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.oszika.provapdm2v2.R;
import com.oszika.provapdm2v2.util.permission.PermissionHelper;

public class MyDialogFragment extends DialogFragment {
    private PermissionHelper permissionHelper;
    public MyDialogFragment(PermissionHelper permissionHelper) {
        this.permissionHelper = permissionHelper;
    }

    // O uso do fragmento ajuda a evitar problemas com as caixas de diálogos, como a perda do estado do diálogo durante rotações de tela.
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("por favor, esse app precisa da persmissao para mostrar o resultado")
                .setTitle("Permissao necessaria")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        permissionHelper.pedirPermissaoNotificacao(getActivity());
                    }
                });

        AlertDialog dialog = builder.create();
        return dialog;
    }//onCreateDialog
}//class