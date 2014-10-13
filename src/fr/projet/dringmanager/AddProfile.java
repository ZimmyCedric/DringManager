package fr.projet.dringmanager;

import java.io.FileNotFoundException;
import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageButton;

public class AddProfile extends Activity {

	protected static final int REQUEST_PICK_IMAGE = 1;
	private ImageButton mImage;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_profile);
		mImage = (ImageButton) findViewById(R.id.imageButton);
		mImage.setOnClickListener(mImageButtonListener);
	}

	private View.OnClickListener mImageButtonListener = new View.OnClickListener() {

		@Override
		public void onClick(View v) {

			Intent pickImageIntent = new Intent(
					Intent.ACTION_PICK,                                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
			startActivityForResult(pickImageIntent, REQUEST_PICK_IMAGE);
		}
	};

	protected void onActivityResult(int requestCode, int resultCode, Intent intent) {

		if (RESULT_OK == resultCode) {
			Uri imageUri = intent.getData();
			Bitmap bitmap;
			try {
				bitmap = MediaStore.Images.Media.getBitmap(
						getContentResolver(), imageUri);
				mImage.setImageBitmap(bitmap);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}


	}

}
