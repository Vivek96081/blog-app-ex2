package blogapp.Repository;

import blogapp.Entity.Moon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MoonRepository extends JpaRepository<Moon, Long> {

}
