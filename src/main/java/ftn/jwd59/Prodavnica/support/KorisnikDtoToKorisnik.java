package ftn.jwd59.Prodavnica.support;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import ftn.jwd59.Prodavnica.model.Korisnik;
import ftn.jwd59.Prodavnica.service.KorisnikService;
import ftn.jwd59.Prodavnica.web.dto.KorisnikDTO;

@Component
public class KorisnikDtoToKorisnik implements Converter<KorisnikDTO, Korisnik>{
	
	@Autowired
	private KorisnikService korisnikService;
	@Override
	public Korisnik convert(KorisnikDTO korisnikDTO) {
		Korisnik entity = null;

		if(korisnikDTO.getId() == null) {
			entity = new Korisnik();
		}else {
			Optional<Korisnik> korisnikOptional = korisnikService.findOne(korisnikDTO.getId());
			if(korisnikOptional.isPresent()){
				entity = korisnikOptional.get();
			}
		}

		if(entity != null) {
			entity.setKorisnickoIme(korisnikDTO.getKorisnickoIme());
			entity.seteMail(korisnikDTO.geteMail());
			entity.setIme(korisnikDTO.getIme());
			entity.setPrezime(korisnikDTO.getPrezime());
		}

		return entity;
	}
}
