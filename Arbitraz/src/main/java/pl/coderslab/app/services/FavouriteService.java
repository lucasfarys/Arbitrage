package pl.coderslab.app.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.app.dto.FavouriteFormDTO;
import pl.coderslab.app.model.Favourite;
import pl.coderslab.app.repositories.FavouriteRepository;

import java.util.List;

@Service
@Transactional
public class FavouriteService {
    private FavouriteRepository favouriteRepository;

    public FavouriteService(FavouriteRepository favouriteRepository) {
        this.favouriteRepository = favouriteRepository;
    }
    public List<Favourite> fiandAllFavouritesByLogin(String login){
        return favouriteRepository.findAllByLogin("lukaszfarys@gmail.com");
    }
    public Favourite findLastAddedFavourite(String login){
        return favouriteRepository.findFirstByLoginOrderByIdDesc(login);
    }
    public void saveFavourite(FavouriteFormDTO favouriteFormDTO){
        Favourite favourite = new Favourite();
        favourite.setLogin(favouriteFormDTO.getLogin());
        favourite.setCoin(favouriteFormDTO.getCoin());
        favourite.setExchangeFirst(favouriteFormDTO.getExchangeFirst());
        favourite.setExchangeSecond(favouriteFormDTO.getExchangeSecond());
        favouriteRepository.save(favourite);
    }
    public List<Favourite> finadAll(){
        return favouriteRepository.findAll();
    }
    public void deleteFavouriteById(Long id){favouriteRepository.deleteById(id);
    }
}
