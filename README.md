DECLARE
    CURSOR c_regulateur IS
        SELECT id_regulateur,
               date_creation,
               NVL(date_fin, TO_DATE('31-01-2100','DD-MM-YYYY')) AS date_fin_regulateur
        FROM   regulateur;

    -- Variables tampons
    v_valeur_toto   VARCHAR2(4000);
    v_date_debut    DATE;
    v_date_fin      DATE;

BEGIN
    FOR r IN c_regulateur LOOP

        -- Initialisation : valeur initiale inconnue (sera prise dans OLD de la première modif)
        v_valeur_toto := NULL;
        v_date_debut  := r.date_creation;
        v_date_fin    := r.date_fin_regulateur;

        -- Parcours des audits
        FOR rec IN (
            SELECT a.date_modif,
                   REGEXP_SUBSTR(a.commentaire, 'toto\(old=([^,]+),new=[^)]*\)', 1, 1, NULL, 1) AS old_toto,
                   REGEXP_SUBSTR(a.commentaire, 'toto\(old=[^,]+,new=([^)]*)\)', 1, 1, NULL, 1) AS new_toto
            FROM   audit a
            WHERE  a.id_regulateur = r.id_regulateur
              AND  REGEXP_LIKE(a.commentaire, 'toto\(old=.*new=.*\)')
            ORDER  BY a.date_modif
        ) LOOP
-- Si première modif, initialiser valeur initiale
            IF v_valeur_toto IS NULL THEN
                v_valeur_toto := rec.old_toto;
            END IF;

            -- Insérer l’état courant jusqu’à la date de la modif
            INSERT INTO table_cible (id_regulateur, valeur_toto, date_debut, date_fin)
            VALUES (r.id_regulateur, v_valeur_toto, v_date_debut, rec.date_modif);

            -- Mettre à jour pour la prochaine itération
            v_valeur_toto := rec.new_toto;
            v_date_debut  := rec.date_modif;

        END LOOP;
END LOOP;

        -- Insérer la dernière valeur courante (soit issue de OLD si aucune modif,
        -- soit issue du dernier NEW si des modifs existent)
        INSERT INTO table_cible (id_regulateur, valeur_toto, date_debut, date_fin)
        VALUES (r.id_regulateur, v_valeur_toto, v_date_debut, v_date_fin);

    END LOOP;

    COMMIT;
END;
/










# mars-rover


<pre>A squad of robotic rovers are to be landed by NASA on a plateau on Mars.
This plateau, which is curiously rectangular, must be navigated by the
rovers so that their on-board cameras can get a complete view of the
surrounding terrain to send back to Earth.
A rover's position and location is represented by a combination of x and y
co-ordinates and a letter representing one of the four cardinal compass
points. The plateau is divided up into a grid to simplify navigation. An
example position might be 0, 0, N, which means the rover is in the bottom
left corner and facing North.
In order to control a rover, NASA sends a simple string of letters. The
possible letters are 'L', 'R' and 'M'. 'L' and 'R' makes the rover spin 90
degrees left or right respectively, without moving from its current spot.
'M' means move forward one grid point, and maintain the same heading.
Assume that the square directly North from (x, y) is (x, y+1).

INPUT:
The first line of input is the upper-right coordinates of the plateau, the
lower-left coordinates are assumed to be 0,0.
The rest of the input is information pertaining to the rovers that have
been deployed. Each rover has two lines of input. The first line gives the
rover's position, and the second line is a series of instructions telling
the rover how to explore the plateau.
The position is made up of two integers and a letter separated by spaces,
corresponding to the x and y co-ordinates and the rover's orientation.
Each rover will be finished sequentially, which means that the second rover
won't start to move until the first one has finished moving.

OUTPUT:
The output for each rover should be its final co-ordinates and heading.

INPUT AND OUTPUT
Test Input:
5 5
1 2 N
LMLMLMLMM
3 3 E
MMRMMRMRRM
Expected Output:
1 3 N
5 1 E

COMMAND LINE:
The program will be runned with this command line:

java -jar rover.jar input.txt
</pre>
