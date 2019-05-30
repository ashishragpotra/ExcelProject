/**
 * 
 */
package net.codejava.fileupload.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.codejava.fileupload.model.GeologicalSections;

/**
 * @author aragpotra
 *
 */
@Repository
public class GeologicalSectionDAOImpl implements GeologicalSectionDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public GeologicalSectionDAOImpl() {
	}

	public GeologicalSectionDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	@Transactional
	public void save(List<GeologicalSections> geologicalSectionsList) {
		sessionFactory.getCurrentSession().save(geologicalSectionsList);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<GeologicalSections> getGeologicalSectionsWithFileId(Long id) {
		String hql = "from GeologicalSections where upload.id=:id";
		return (List<GeologicalSections>) sessionFactory.getCurrentSession().createQuery(hql).setParameter("id", id)
				.list();
	}
	
	@Override
	@Transactional
	public int deleteFromGeologicalSections(Long fileId, Long geologicalId) {
		String deleteHql = "delete from GeologicalSections where upload.id=:fileId and id=:geologicalId";
		return sessionFactory.getCurrentSession().createQuery(deleteHql).setParameter("fileId", fileId)
				.setParameter("geologicalId", geologicalId).executeUpdate();
	}
}
