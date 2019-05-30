/**
 * 
 */
package net.codejava.fileupload.dao;

import java.util.List;

import net.codejava.fileupload.model.GeologicalSections;

/**
 * @author aragpotra
 *
 */
public interface GeologicalSectionDAO {

	void save(List<GeologicalSections> geologicalSectionsList);
	
	List<GeologicalSections> getGeologicalSectionsWithFileId(Long id);

	int deleteFromGeologicalSections(Long fileId, Long geologicalId);
}
