package pl.coderslab.app.services;

import org.springframework.stereotype.Service;
import pl.coderslab.app.dto.FavouriteFormDTO;
import pl.coderslab.app.model.Favourite;
import pl.coderslab.app.repositories.FavouriteRepository;

import java.util.List;

@Service
public class FavouriteService {
    private FavouriteRepository favouriteRepository;

    public FavouriteService(FavouriteRepository favouriteRepository) {
        this.favouriteRepository = favouriteRepository;
    }
    public List<Favourite> fiandAllFavouritesByLogin(String login){
        return favouriteRepository.findAllByLogin("lukaszfarys@gmail.com");
    }
    public void saveFavourite(FavouriteFormDTO favouriteFormDTO){
        Favourite favourite = new Favourite();
        favourite.setLogin(favouriteFormDTO.getLogin());
        favourite.setCoin(favouriteFormDTO.getCoin());
        favourite.setExchangeFirstId(favouriteFormDTO.getExchange_first());
        favourite.setExchangeSecondId(favouriteFormDTO.getExchange_second());
        favouriteRepository.save(favourite);
    }
}
