package tacos.service.impl;

import org.springframework.stereotype.Service;
import tacos.entity.Taco;
import tacos.repository.TacoRepository;
import tacos.service.TacoService;

@Service
public class TacoServiceImpl implements TacoService {

    private final TacoRepository tacoRepository;

    public TacoServiceImpl(TacoRepository tacoRepository) {
        this.tacoRepository = tacoRepository;
    }

    @Override
    public Taco create(Taco taco) {
        return tacoRepository.save(taco);
    }
}
