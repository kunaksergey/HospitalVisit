package ua.shield.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.shield.entity.Chield;
import ua.shield.repository.ChieldRepository;

@Service("chieldService")
public class ChieldServiceImpl implements ChieldService{
    private final ChieldRepository chieldRepository;

    @Autowired
    public ChieldServiceImpl(ChieldRepository chieldRepository) {
        this.chieldRepository = chieldRepository;
    }

    @Override
    public Chield add(Chield chield) {
        return chieldRepository.save(chield);
    }

    @Override
    public Chield update(Chield chield) {
        return chieldRepository.save(chield);
    }
}
