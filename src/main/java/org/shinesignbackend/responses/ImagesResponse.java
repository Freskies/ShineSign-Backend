package org.shinesignbackend.responses;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.shinesignbackend.entities.UploadedImage;

import java.util.List;

public record ImagesResponse(
	List<ImageResponse> images
) {
	@Contract ("_ -> new")
	public static @NotNull ImagesResponse fromUploadedImage(@NotNull List<UploadedImage> images) {
		return new ImagesResponse(
			images.stream()
				.map(ImageResponse::fromUploadedImage)
				.toList()
		);
	}
}
