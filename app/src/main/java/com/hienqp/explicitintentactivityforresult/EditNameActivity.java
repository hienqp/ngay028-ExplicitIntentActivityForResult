package com.hienqp.explicitintentactivityforresult;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditNameActivity extends AppCompatActivity {
    public static final String KEY_EDIT = "KEY_EDIT";

    EditText editTextEditName;
    Button buttonConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_name);

        editTextEditName = (EditText) findViewById(R.id.editText_edit_name);
        buttonConfirm = (Button) findViewById(R.id.button_confirm);

        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // dữ liệu Result dùng để trả về
                String newName = editTextEditName.getText().toString();

                // khởi tạo 1 Intent, mà Intent này không dùng để kích hoạt Activity
                Intent data = new Intent();

                // đưa dữ liệu vào trong Intent
                data.putExtra(EditNameActivity.KEY_EDIT, newName);

                // setResult EditNameActivity sẽ trả về cho MainActivity đã gọi nó (start)
                setResult(Activity.RESULT_OK, data);

                // sau khi set Result phải gọi finish() để đóng EditNameActivity
                // phương thức finish() phải gọi sau setResult()
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        // nếu user không có hành động xác nhận như nhấn vào Button xác nhận
        // mà nhấn vào nút Back
        // ta đặt setResult là RESULT_CANCELED thể hiện lấy dữ liệu thất bại
        // khi này sẽ không có data được trả về, chỉ đơn giản là khi nhấn Back thì quay trở lại Activity trước
        setResult(Activity.RESULT_CANCELED);

        super.onBackPressed();
    }
}