package com.hienqp.explicitintentactivityforresult;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    /* REQUEST_CODE là một giá trị int dùng để định danh mỗi request.
    Khi nhận được kết quả,
    hàm callback sẽ trả về cùng REQUEST_CODE này để ta có thể xác định và xử lý kết quả. */
    private static final int REQUEST_CODE_EXAMPLE = 4234124;

    TextView textViewDisplayName;
    Button buttonEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewDisplayName = (TextView) findViewById(R.id.textView_display_name);
        buttonEdit = (Button) findViewById(R.id.button_edit);

        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tạo 1 Intent để start EditNameActivity
                Intent request = new Intent(MainActivity.this, EditNameActivity.class);

                // start EditNameActivity với REQUEST_CODE_EXAMPLE đã được khai báo trước đó
                startActivityForResult(request, REQUEST_CODE_EXAMPLE);
            }
        });
    }

    // Khi Result được trả về từ Activity khác, hàm onActivityResult sẽ được gọi.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Kiểm tra requestCode có trùng với REQUEST_CODE vừa dùng
        if (requestCode == MainActivity.REQUEST_CODE_EXAMPLE) {

            // resultCode được set bởi EditNameActivity
            // RESULT_OK chỉ ra rằng kết quả này đã thành công
            if (resultCode == Activity.RESULT_OK) {

                // Nhận dữ liệu Intent trả về từ EditNameActivity
                String editName = data.getStringExtra(EditNameActivity.KEY_EDIT);

                textViewDisplayName.setText(editName);

                Toast.makeText(MainActivity.this, "Không có dữ liệu trả về từ EditNameActivity", Toast.LENGTH_SHORT).show();

                // có thể thông báo yêu cầu user thực hiện lại hành động
                // vì khi khối else được thực hiện có nghĩa là user không hành động theo ta muốn
                // mà thao tác sai là nhấn vào nút Back mà không xác nhận Button
                Toast.makeText(MainActivity.this, "Vui lòng nhập dữ liệu và nhấn xác nhận", Toast.LENGTH_SHORT).show();
            }
        }
    }
}