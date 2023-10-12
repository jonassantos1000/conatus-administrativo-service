package br.com.app.conatus.repositories;

import java.util.Objects;
import java.util.Optional;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;

public class JPAImpl<T, I> extends SimpleJpaRepository<T, I> {

	private final JpaEntityInformation<T, I> entityInformation;
	private final EntityManager entityManager;

	public JPAImpl(final JpaEntityInformation<T, I> entityInformation, final EntityManager entityManager) {
		super(entityInformation, entityManager);
		this.entityInformation = entityInformation;
		this.entityManager = entityManager;
	}

	  @Override
	  public Optional<T> findById(@NonNull final I id) {
	    final Class<T> entityType = this.entityInformation.getJavaType();
	    Objects.requireNonNull(id, "ID can not be null for Type [" + entityType + "]");

	    final CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
	    final CriteriaQuery<T> cq = criteriaBuilder.createQuery(entityType);

	    cq.where(criteriaBuilder.equal(cq.from(entityType).get(this.getId()), id));
	    final TypedQuery<T> q = this.entityManager.createQuery(cq);

	    return q.getResultStream().findFirst();
	  }

	  private String getId() {
	    return this.entityInformation.getRequiredIdAttribute().getName();
	  }
}
