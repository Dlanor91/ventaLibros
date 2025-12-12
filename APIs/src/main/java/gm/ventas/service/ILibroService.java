package gm.ventas.service;

import gm.ventas.model.Libro;

public interface ILibroService {

    Libro buscarPorIsbn(String isbn);

    void descontarStock();
}
