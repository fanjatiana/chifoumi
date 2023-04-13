package Service;

import Model.Chifumi;

public class ComputerRandomService {

    public String play(){
        Chifumi chifumi =new Chifumi("Chi","Fu", "Mi");

        String computerChoice = "";
        int nb = (int) (Math.random() * 3);
        if ( nb == 0 )
        { computerChoice=chifumi.getChi(); }

        if ( nb == 1 )
        { computerChoice=chifumi.getFu(); }

        if ( nb == 2 )
        { computerChoice=chifumi.getMi(); }

        return computerChoice;
    }
}
