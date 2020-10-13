package br.com.zup.bootcamp.fleamarketapi.features.account;

import br.com.zup.bootcamp.fleamarketapi.model.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Long> {

}
