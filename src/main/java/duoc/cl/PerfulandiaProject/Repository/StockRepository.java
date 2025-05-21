package duoc.cl.PerfulandiaProject.Repository;

import duoc.cl.PerfulandiaProject.Model.Stock;
import duoc.cl.PerfulandiaProject.Model.StockId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock, StockId> {
}