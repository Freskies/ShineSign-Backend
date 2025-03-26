package org.shinesignbackend.responses;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.shinesignbackend.entities.UploadedImage;

import java.util.UUID;

public record ImageResponse(
	UUID id,
	String url
) {
	@Contract ("_ -> new")
	public static @NotNull ImageResponse fromUploadedImage(@NotNull UploadedImage image) {
		return new ImageResponse(
			image.getId(),
			image.getUrl()
		);
	}
}
