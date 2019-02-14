package com.example.sauldelgado.klavadoapp.TipoLavado.Presenter.Activity;

import com.example.sauldelgado.klavadoapp.TipoLavado.Interator.TipoLavadoInterator;
import com.example.sauldelgado.klavadoapp.TipoLavado.Interator.TipoLavadoInteratorImpl;
import com.example.sauldelgado.klavadoapp.TipoLavado.View.Activity.TipoLavadoView;
import com.example.sauldelgado.klavadoapp.TipoLavado.View.Fragment.LavadoEcologicoFragment;
import com.example.sauldelgado.klavadoapp.TipoLavado.View.Fragment.LavadoPremiumFragment;

public class TipoLavadoPresenterImpl implements TipoLavadoPresenter, OnTipoLavadoFinish{

    private TipoLavadoView tipoLavadoView;
    private TipoLavadoInterator tipoLavadoInterator;
    LavadoPremiumFragment lavadoPremiumFragment;
    LavadoEcologicoFragment lavadoEcologicoFragment;

    public TipoLavadoPresenterImpl(TipoLavadoView tipoLavadoView) {
        this.tipoLavadoView = tipoLavadoView;
        tipoLavadoInterator = new TipoLavadoInteratorImpl();
    }
}
