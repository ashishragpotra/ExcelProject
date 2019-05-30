package net.codejava.fileupload.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.codejava.fileupload.model.UploadFile;

@Repository
public class FileUploadDAOImpl implements FileUploadDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public FileUploadDAOImpl() {
	}

	public FileUploadDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	@Transactional
	public void save(UploadFile uploadFile) {
		sessionFactory.getCurrentSession().saveOrUpdate(uploadFile);
	}

	

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<UploadFile> getListOfUploadFiles() {
		return (List<UploadFile>) sessionFactory.getCurrentSession().createSQLQuery("SELECT * FROM UploadFile")
				.addEntity(UploadFile.class).list();
	}

	
	@Override
	@Transactional
	public int deleteFileAndGeologicalSections(Long fileId) {
		String deleteHql = "delete from UploadFile where id=:fileId";
		return sessionFactory.getCurrentSession().createQuery(deleteHql).setParameter("fileId", fileId).executeUpdate();
	}

	
}