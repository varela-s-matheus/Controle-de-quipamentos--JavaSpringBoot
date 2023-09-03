package br.com.senai.contactapp.domain.equipament;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.senai.contactapp.infra.EquipamentRepository;
@Service
public class EquipamentService {

    @Autowired
    private EquipamentRepository repository;

    private List<Equipament> equipaments = new ArrayList<Equipament>();

    public boolean createEquipament(Equipament equipament) {
        if(equipament == null) {
            return false;
        }
        equipament = repository.insert(equipament);
        return equipament.getId() != null;
    }

    public boolean update(Equipament equipament) {
        if(equipament == null) {
            return false;
        }
        if(findById(equipament.getId()) == null) {
            return false;
        }
        equipament = repository.update(equipament);

        return equipament.getId() != null;
    }

    public boolean save(Equipament equipament) {
        if(equipament.getId() != null) {
            return update(equipament);
        }
        return createEquipament(equipament);
    }

    public List<Equipament> findAll(){
        return repository.findAll();
    }

    public Equipament findById(final UUID id) {
        Equipament equipament = findAll()
                .stream()
                .filter((current)-> current.getId().equals(id))
                .findFirst()
                .orElse(null);
        return equipament;
    }

    public boolean removeEquipament(final UUID id) {
        if(findById(id) == null) {
            return false;
        }

        return repository.delete(id);

    }
}
