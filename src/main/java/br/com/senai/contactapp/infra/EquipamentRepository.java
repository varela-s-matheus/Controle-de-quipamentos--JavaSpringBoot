package br.com.senai.contactapp.infra;

import br.com.senai.contactapp.domain.equipament.Equipament;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@Repository

public class EquipamentRepository {

    @Autowired
    private JdbcTemplate db;

    public List<Equipament> findAll(){
        String sql = "select * from equipament";
        return db.query(sql,(resultset,index)-> {
            return toEquipament(resultset);
        });
    }

    private Equipament toEquipament(ResultSet resultset) throws SQLException {
        return Equipament.builder()
                .id(UUID.fromString(resultset.getString("id")))
                .name(resultset.getString("name"))
                .tipo(resultset.getString("tipo"))
                .codigoBarras(resultset.getString("codigoBarras"))
                .descricao(resultset.getString("descricao"))
                .build();
    }

    public Equipament insert(Equipament equipaments) {
        UUID id = UUID.randomUUID();
        String sql = "insert into equipament (id, name, tipo, codigoBarras, descricao) values (?,?,?,?,?)";
        int result = db.update(sql,
                id,equipaments.getName(), equipaments.getTipo(),equipaments.getCodigoBarras(), equipaments.getDescricao());
        if(result == 1) {
            equipaments.setId(id);
        }
        return equipaments;
    }

    public Equipament findById(UUID id) {
        String sql = "select * from equipament where id = ?";
        return db.queryForObject(sql, (resultset,index)->{
            return toEquipament(resultset);
        },id);
    }


    public Equipament update(Equipament equipament) {
        UUID id = UUID.randomUUID();
        String sql = "update equipament set name = ?, tipo = ?, codigoBarras = ?, descricao = ?  where id = ?";
        int result = db.update(sql, equipament.getName(),equipament.getTipo(),equipament.getCodigoBarras(),equipament.getDescricao(),equipament.getId());
        if(result == 1) {
            equipament.setId(id);
        }
        return equipament;
    }

    public boolean delete(UUID id) {
        String sql = "delete from equipament where id = ?";
        int result = db.update(sql, id);

        return true;
    }


}
