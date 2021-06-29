package com.andr7st.spring.horas.app.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.andr7st.spring.horas.app.models.HoraPais;

//import java.time.*;
//import java.time.format.DateTimeFormatter;
//import java.time.temporal.ChronoUnit;
import java.util.*;

@Controller
@RequestMapping("/")
public class Indice {

	@GetMapping(value = { "", "/", "/index", "/home" }) // Recomendado
	public String index(Model model) {

		model.addAttribute("hora", this.dameHora("America/Bogota"));
		model.addAttribute("hora2", this.dameHora("America/Santiago"));

		model.addAttribute("titulo", "pagina");
		model.addAttribute("saludo", "Hola mundo, desde la red!!");
		return "index";

	}

	@ModelAttribute("laHora")
	public String laHora() {
		return this.dameHora("America/Bogota");
	}

	public String dameHora(String zonaHoraria) {

		Calendar tiempo2 = Calendar.getInstance();
		tiempo2.setTimeZone(TimeZone.getTimeZone(zonaHoraria));

		int hh = tiempo2.get(Calendar.HOUR_OF_DAY);
		int mm = tiempo2.get(Calendar.MINUTE);
		int ss = tiempo2.get(Calendar.SECOND);
		int am = tiempo2.get(Calendar.AM_PM);

		if (hh > 12) {
			hh -= 12;
		}

		// sentencia con operador ternario:
		String horas = /* hh <10 ? "0"+ hh: */String.valueOf(hh);
		String minutos = mm < 10 ? "0" + mm : String.valueOf(mm);
		String segundos = ss < 10 ? "0" + ss : String.valueOf(ss);
		String manana = am != 0 ? "PM" : "AM";

		return horas.concat(":").concat(minutos).concat(":").concat(segundos).concat(" ").concat(manana);

	}

	@ModelAttribute("horasPaises")
	public List<HoraPais> horasDePaises() {

		List<HoraPais> paises = Arrays.asList(
				
				new HoraPais("Colombia",   this.dameHora("America/Bogota")),
				//new HoraPais("Hora media", this.dameHora("GMT")), // "Hora Media de Greenwich")),
				new HoraPais("US / Pacific",   this.dameHora("US/Pacific")),
				new HoraPais("Chile",      this.dameHora("America/Santiago")),
				new HoraPais("España",     this.dameHora("Europe/Madrid")),
				new HoraPais("Canada",     this.dameHora("America/Vancouver")),
				new HoraPais("Perú",       this.dameHora("America/Lima")),
				new HoraPais("Venezuela",  this.dameHora("America/Caracas")),
				new HoraPais("Argentina",  this.dameHora("America/Argentina/La_Rioja")),
				new HoraPais("Mexico",     this.dameHora("America/Mexico_City")));

		return paises;
	}

}
