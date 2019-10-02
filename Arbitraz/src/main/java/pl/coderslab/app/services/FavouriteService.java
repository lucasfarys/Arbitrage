package pl.coderslab.app.services;

import org.springframework.stereotype.Service;
import pl.coderslab.app.dto.FavouriteFormDTO;
import pl.coderslab.app.model.Favourite;
import pl.coderslab.app.repositories.FavouriteRepository;

@Service
public class FavouriteService {
    private FavouriteRepository favouriteRepository;

    public FavouriteService(FavouriteRepository favouriteRepository) {
        this.favouriteRepository = favouriteRepository;
    }
    public void saveFavourite(FavouriteFormDTO favouriteFormDTO){
        Favourite favourite = new Favourite();
        favourite.setCoin(favouriteFormDTO.getCoin());
        favourite.setExchange_first(favouriteFormDTO.getExchange_first());
        favourite.setExchange_second(favouriteFormDTO.getExchange_second());
        favourite.setLogin(favouriteFormDTO.getLogin());
        favouriteRepository.save(favourite);
    }
}
