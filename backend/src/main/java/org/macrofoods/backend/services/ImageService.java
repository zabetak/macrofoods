package org.macrofoods.backend.services;

import javax.persistence.EntityManager;

import org.macrofoods.backend.dto.ImageDTO;
import org.macrofoods.backend.entities.jpa.Image;

public final class ImageService {

	private final EntityManager em;

	public ImageService(EntityManager entityManager) {
		this.em = entityManager;
	}

	public ImageDTO findBy(int id) {
		Image img = em.find(Image.class, id);
		ImageDTO dto = new ImageDTO();
		dto.setId(id);
		if (img != null)
			dto.setData(new String(img.getData()));
		return dto;
	}
}
