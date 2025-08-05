package com.movieflix.Service;

import com.movieflix.entity.StreamingEntity;
import com.movieflix.repository.StreamingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StreamingService {

    @Autowired
    private StreamingRepository streamingRepository;

    public  StreamingEntity saveEntity(StreamingEntity entity){
        return streamingRepository.save(entity);
    }

    public Optional<StreamingEntity> findById(Long id){return streamingRepository.findById(id);}

    public List<StreamingEntity> findAll(){
        return streamingRepository.findAll();
    }

    public void deleteId(Long id ){
        streamingRepository.deleteById(id);
    }

}
