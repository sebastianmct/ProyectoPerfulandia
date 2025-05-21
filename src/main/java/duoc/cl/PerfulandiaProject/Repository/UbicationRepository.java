package duoc.cl.PerfulandiaProject.Repository;

import duoc.cl.PerfulandiaProject.Model.Ubication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UbicationRepository extends JpaRepository<Ubication, Integer> { // Assuming Integer as ID
}