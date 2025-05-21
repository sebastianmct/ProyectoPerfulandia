package duoc.cl.PerfulandiaProject.Repository;

import duoc.cl.PerfulandiaProject.Model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

}
