package vn.edu.ntu.thanhnuong.hienthisms_content;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import java.util.Calendar;

public class LuaChonFragment extends Fragment implements View.OnClickListener{


    public static final String KEY_TU_NGAY = "tu_ngay";
    public static final String KEY_DEN_NGAY = "den_ngay";
    EditText edtTuNgay, edtDenNgay;
    ImageView imvTuNgay, imvDenNgay;
    Button btnHienThi;
    NavController navController;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lua_chon, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        edtTuNgay = view.findViewById(R.id.edtNgayDi);
        edtDenNgay = view.findViewById(R.id.edtNgayDen);
        imvTuNgay = view.findViewById(R.id.imvdateDi);
        imvDenNgay = view.findViewById(R.id.imvdateden);
        btnHienThi = view.findViewById(R.id.btnHienThi);
        imvTuNgay.setOnClickListener(this);
        imvDenNgay.setOnClickListener(this);
        btnHienThi.setOnClickListener(this);
        navController = NavHostFragment.findNavController(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.imvdateDi: ThietLapNgay(0);break;
            case R.id.imvdateden: ThietLapNgay(1);break;
            case R.id.btnHienThi: hienThiSMS();break;
        }
    }

    private void ThietLapNgay(final int luachon){
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(year)
                        .append("-")
                        .append(++month)
                        .append("-")
                        .append(dayOfMonth);

                if(luachon == 0){
                    edtTuNgay.setText(stringBuilder.toString());
                }else
                    edtDenNgay.setText(stringBuilder.toString());
            }
        };
        DatePickerDialog pickerDialog = new DatePickerDialog(
                getActivity(),
                listener,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );
        pickerDialog.show();
    }

    private void hienThiSMS()
    {
        if(edtTuNgay.getText().toString().length()>0 && edtDenNgay.getText().toString().length()>0){
            Bundle data = new Bundle();
            data.putCharSequence(KEY_TU_NGAY, edtTuNgay.getText().toString());
            data.putCharSequence(KEY_DEN_NGAY, edtDenNgay.getText().toString());
            navController.navigate(R.id.action_luaChonFragment_to_SMSFragment, data);
        }
    }
}
