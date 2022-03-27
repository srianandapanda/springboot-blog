package nesteen.springboot.project.SpringBootProject.service;

import nesteen.springboot.project.SpringBootProject.dao.TagsRepository;
import nesteen.springboot.project.SpringBootProject.entity.Tags;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TagsServiceUse implements TagsService{

    private TagsRepository tagsRepository;

    @Autowired
    public TagsServiceUse(TagsRepository theTagRepository) {
        tagsRepository = theTagRepository;
    }

    @Override
    public void save(Tags theTags){
        tagsRepository.save(theTags);
    }

    @Override
    public String findStatusById(int theId) {
        List<Tags> result = tagsRepository.findAllTags(theId);
        String tagString = "";
        //List<String> tags= null;
        for(Tags tag:result)
            tagString=tagString+tag.getName();

        return tagString;
    }

    @Override
    public List<Tags> findTagByName(String name) {
        return tagsRepository.findTagByName(name);
    }

    @Override
    public List<Tags> findByNameIgnoreCaseLike(String theTag) {
        return tagsRepository.findByNameIgnoreCaseLike(theTag);
    }

    @Override
    public Tags findTagById(int tagId) {
        Optional<Tags> result = tagsRepository.findById(tagId);
        Tags theTags = result.get();
        return theTags;
    }

    @Override
    public List<Tags> findAll() {
        return tagsRepository.findAll();
    }

    @Override
    public int findTagIdByName(String name) {
        return tagsRepository.findTagIdByName(name);
    }
}
