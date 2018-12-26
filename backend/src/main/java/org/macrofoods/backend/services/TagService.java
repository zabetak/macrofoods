package org.macrofoods.backend.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

import org.macrofoods.backend.dto.TagDTO;
import org.macrofoods.backend.entities.jpa.LangCode;
import org.macrofoods.backend.entities.jpa.Tag;
import org.macrofoods.backend.entities.jpa.TagDescription;
import org.macrofoods.backend.entities.jpa.TagDescription_;
import org.macrofoods.backend.entities.jpa.Tag_;

public final class TagService {

	private final EntityManager em;
	private static final String TAG_PATTER_PARAM = "tagPattern";
	private final TypedQuery<Tuple> findByNameQuery;

	public TagService(EntityManager entityManager, LangCode langCode) {
		this.em = entityManager;
		CriteriaBuilder builder = this.em.getCriteriaBuilder();
		CriteriaQuery<Tuple> q = builder.createTupleQuery();
		Root<Tag> tags = q.from(Tag.class);
		Join<Tag, TagDescription> tagDesc = tags.join(Tag_.descriptions);
		q.where(builder.and(builder.equal(tagDesc.get(TagDescription_.langCode), langCode),
				builder.like(tagDesc.get(TagDescription_.name), builder.parameter(String.class, TAG_PATTER_PARAM))));
		q.multiselect(tags.get(Tag_.id), tagDesc.get(TagDescription_.name));
		this.findByNameQuery = em.createQuery(q);
	}

	public List<TagDTO> findByName(String tagName) {
		String tagPattern = tagName.toUpperCase() + "%";
		findByNameQuery.setParameter(TAG_PATTER_PARAM, tagPattern);
		List<TagDTO> tags = new ArrayList<TagDTO>();
		for (Tuple t : findByNameQuery.getResultList()) {
			TagDTO tag = new TagDTO();
			tag.setId(t.get(0, Integer.class));
			tag.setDescription(t.get(1, String.class));
			tags.add(tag);
		}
		return tags;
	}
}
