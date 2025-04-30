module Dibujo where

-- Definicion  del lenguaje
data Dibujo a = Basica a 
              | Rotar (Dibujo a)
              | Apilar Int Int (Dibujo a) (Dibujo a)
              | Encimar (Dibujo a) (Dibujo a)
              | Resize Int (Dibujo a)
              deriving(Show, Eq)


-- Funcion Map (de Basicas) para nuestro sub-lenguaje.
mapDib :: (a -> b) -> Dibujo a -> Dibujo b
mapDib f (Basica x) = Basica (f x) 
mapDib f (Rotar d1) = Rotar (mapDib f d1)
mapDib f (Apilar n m d1 d2) = Apilar n m (mapDib f d1) (mapDib f d2)
mapDib f (Encimar d1 d2) = Encimar (mapDib f d1) (mapDib f d2)
mapDib f (Resize n d1) = Resize n (mapDib f d1)


-- Funcion Fold para nuestro sub-lenguaje.
foldDib :: (a -> b) -> (b -> b) ->
       (Int -> Int -> b -> b -> b) -> 
       (b -> b -> b) ->
       (Int -> b -> b) ->
       Dibujo a -> b

foldDib sB sR sA sEn sRe d =
    let foldDibRecursiva = foldDib sB sR sA sEn sRe
    in case d of
        Basica x -> sB x
        Rotar d -> sR $ foldDibRecursiva d
        Apilar m n d1 d2 -> sA m n (foldDibRecursiva d1) (foldDibRecursiva d2)
        Encimar d1 d2 -> sEn (foldDibRecursiva d1) (foldDibRecursiva d2)
        Resize n d -> sRe n (foldDibRecursiva d)


esMultiplo :: Int -> Int -> Bool
esMultiplo x y = x `mod` y == 0|| y `mod` x == 0

--COMPLETAR (EJERCICIO 1-a)
toBool:: Dibujo (Int,Int) -> Dibujo Bool
toBool (Basica (x,y)) = Basica (esMultiplo x y)
toBool (Rotar (d)) = Rotar (toBool d)
toBool (Apilar f1 f2 d1 d2) = Apilar f1 f2 (toBool d1) (toBool d2)
toBool (Encimar d1 d2 ) = Encimar (toBool d1) (toBool d2)
toBool (Resize n d) = Resize n (toBool d)

--COMPLETAR (EJERCICIO 1-b)
toBool2:: Dibujo (Int,Int) -> Dibujo Bool
toBool2 = mapDib (\(x,y) ->  x `mod` y == 0|| y `mod` x == 0)


--COMPLETAR (EJERCICIO 1-c)
profundidad:: Dibujo a -> Int
profundidad (Basica a) = 1
profundidad (Rotar d) = 1 + profundidad d
profundidad (Apilar _ _ d1 d2) = 1 + max (profundidad d1) (profundidad d2)
profundidad (Encimar d1 d2) = 1 + max (profundidad d1) (profundidad d2)
profundidad (Resize n d) = 1 + profundidad d

--COMPLETAR (EJERCICIO 1-d)
profundidad2:: Dibujo a -> Int
profundidad2 = foldDib
                (const 1)
                (\d -> 1 + d)
                (\_ _ d1 d2 -> 1 + max d1 d2)
                (\d1 d2 -> 1 + max d1 d2)
                (\_ d -> 1 + d)

