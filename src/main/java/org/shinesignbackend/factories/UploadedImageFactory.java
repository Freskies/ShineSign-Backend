package org.shinesignbackend.factories;

import org.jetbrains.annotations.NotNull;
import org.shinesignbackend.entities.UploadedImage;

public class UploadedImageFactory {
	public static @NotNull UploadedImage createUploadedImage (String url) {
		UploadedImage uploadedImage = new UploadedImage();
		uploadedImage.setUrl(url);
		return uploadedImage;
	}
}
