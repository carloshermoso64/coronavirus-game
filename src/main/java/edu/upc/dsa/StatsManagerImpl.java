package edu.upc.dsa;

import edu.upc.dsa.models.Stats;
import org.apache.log4j.Logger;

import java.util.LinkedList;
import java.util.List;

public class StatsManagerImpl implements StatsManager {
    private static StatsManager instance;
    protected List<Stats> stats;
    final static Logger logger = Logger.getLogger(StatsManagerImpl.class);

    private StatsManagerImpl() {
        this.stats = new LinkedList<>();
    }

    public static StatsManager getInstance() {
        if (instance==null) instance = new StatsManagerImpl();
        return instance;
    }

    public int size() {
        int ret = this.stats.size();
        logger.info("size " + ret);

        return ret;
    }

    public Stats addStats(Stats s) {
        logger.info("new Stats " + s);

        this.stats.add (s);
        logger.info("new Stats added");
        return s;
    }

    public Stats addStats(int puntuacion, int dias, int salud, int alimentos, int entretenimiento) {
        return this.addStats(new Stats(puntuacion, dias, salud, alimentos, entretenimiento));
    }

    public Stats getStats(String id) {
        logger.info("getStats("+id+")");

        for (Stats s: this.stats) {
            if (s.getId().equals(id)) {
                logger.info("getStats("+id+"): "+s);

                return s;
            }
        }

        logger.warn("not found " + id);
        return null;
    }

    public List<Stats> findAll() {
        return this.stats;
    }

    @Override
    public void deleteStats(String id) {

        Stats s = this.getStats(id);
        if (s==null) {
            logger.warn("not found " + s);
        }
        else logger.info(s+" deleted ");

        this.stats.remove(s);

    }

    @Override
    public Stats updateStats(Stats p) {
        Stats s = this.getStats(p.getId());

        if (s!=null) {
            logger.info(p+" rebut!!!! ");

            s.setPuntuacion(p.getPuntuacion());
            s.setDias(p.getDias());
            s.setSalud(p.getSalud());
            s.setAlimentos(p.getAlimentos());
            s.setEntretenimiento(p.getEntretenimiento());

            logger.info(s+" updated ");
        }
        else {
            logger.warn("not found "+p);
        }

        return s;
    }
}