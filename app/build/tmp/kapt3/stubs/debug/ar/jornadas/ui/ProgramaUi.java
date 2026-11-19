package ar.jornadas.ui;

/**
 * Estado único de la pantalla del programa.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bv\u0018\u00002\u00020\u0001:\u0004\u0002\u0003\u0004\u0005\u0082\u0001\u0004\u0006\u0007\b\t\u00a8\u0006\n"}, d2 = {"Lar/jornadas/ui/ProgramaUi;", "", "Cargando", "Datos", "Guardados", "SinDatos", "Lar/jornadas/ui/ProgramaUi$Cargando;", "Lar/jornadas/ui/ProgramaUi$Datos;", "Lar/jornadas/ui/ProgramaUi$Guardados;", "Lar/jornadas/ui/ProgramaUi$SinDatos;", "app_debug"})
public abstract interface ProgramaUi {
    
    /**
     * Todavía no se sabe nada (primera lectura de Room o primera descarga sin edición previa).
     */
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u00c6\n\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0013\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u00d6\u0003J\t\u0010\u0007\u001a\u00020\bH\u00d6\u0001J\t\u0010\t\u001a\u00020\nH\u00d6\u0001\u00a8\u0006\u000b"}, d2 = {"Lar/jornadas/ui/ProgramaUi$Cargando;", "Lar/jornadas/ui/ProgramaUi;", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "app_debug"})
    public static final class Cargando implements ar.jornadas.ui.ProgramaUi {
        @org.jetbrains.annotations.NotNull()
        public static final ar.jornadas.ui.ProgramaUi.Cargando INSTANCE = null;
        
        private Cargando() {
            super();
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public java.lang.String toString() {
            return null;
        }
    }
    
    /**
     * Edición vigente; con lista vacía significa «sin charlas programadas».
     */
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u0007J\u000f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0003J\t\u0010\r\u001a\u00020\u0006H\u00c6\u0003J#\u0010\u000e\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006H\u00c6\u0001J\u0013\u0010\u000f\u001a\u00020\u00062\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u00d6\u0003J\t\u0010\u0012\u001a\u00020\u0013H\u00d6\u0001J\t\u0010\u0014\u001a\u00020\u0015H\u00d6\u0001R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\u00a8\u0006\u0016"}, d2 = {"Lar/jornadas/ui/ProgramaUi$Datos;", "Lar/jornadas/ui/ProgramaUi;", "charlas", "", "Lar/jornadas/data/Charla;", "actualizando", "", "(Ljava/util/List;Z)V", "getActualizando", "()Z", "getCharlas", "()Ljava/util/List;", "component1", "component2", "copy", "equals", "other", "", "hashCode", "", "toString", "", "app_debug"})
    public static final class Datos implements ar.jornadas.ui.ProgramaUi {
        @org.jetbrains.annotations.NotNull()
        private final java.util.List<ar.jornadas.data.Charla> charlas = null;
        private final boolean actualizando = false;
        
        public Datos(@org.jetbrains.annotations.NotNull()
        java.util.List<ar.jornadas.data.Charla> charlas, boolean actualizando) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<ar.jornadas.data.Charla> getCharlas() {
            return null;
        }
        
        public final boolean getActualizando() {
            return false;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<ar.jornadas.data.Charla> component1() {
            return null;
        }
        
        public final boolean component2() {
            return false;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final ar.jornadas.ui.ProgramaUi.Datos copy(@org.jetbrains.annotations.NotNull()
        java.util.List<ar.jornadas.data.Charla> charlas, boolean actualizando) {
            return null;
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public java.lang.String toString() {
            return null;
        }
    }
    
    /**
     * La última descarga falló: se muestran los datos de la última edición guardada.
     */
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u0007J\u000f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0003J\t\u0010\r\u001a\u00020\u0006H\u00c6\u0003J#\u0010\u000e\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006H\u00c6\u0001J\u0013\u0010\u000f\u001a\u00020\u00062\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u00d6\u0003J\t\u0010\u0012\u001a\u00020\u0013H\u00d6\u0001J\t\u0010\u0014\u001a\u00020\u0015H\u00d6\u0001R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\u00a8\u0006\u0016"}, d2 = {"Lar/jornadas/ui/ProgramaUi$Guardados;", "Lar/jornadas/ui/ProgramaUi;", "charlas", "", "Lar/jornadas/data/Charla;", "actualizando", "", "(Ljava/util/List;Z)V", "getActualizando", "()Z", "getCharlas", "()Ljava/util/List;", "component1", "component2", "copy", "equals", "other", "", "hashCode", "", "toString", "", "app_debug"})
    public static final class Guardados implements ar.jornadas.ui.ProgramaUi {
        @org.jetbrains.annotations.NotNull()
        private final java.util.List<ar.jornadas.data.Charla> charlas = null;
        private final boolean actualizando = false;
        
        public Guardados(@org.jetbrains.annotations.NotNull()
        java.util.List<ar.jornadas.data.Charla> charlas, boolean actualizando) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<ar.jornadas.data.Charla> getCharlas() {
            return null;
        }
        
        public final boolean getActualizando() {
            return false;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<ar.jornadas.data.Charla> component1() {
            return null;
        }
        
        public final boolean component2() {
            return false;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final ar.jornadas.ui.ProgramaUi.Guardados copy(@org.jetbrains.annotations.NotNull()
        java.util.List<ar.jornadas.data.Charla> charlas, boolean actualizando) {
            return null;
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public java.lang.String toString() {
            return null;
        }
    }
    
    /**
     * Falla sin ninguna edición previa: se ofrece reintentar.
     */
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u00c6\n\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0013\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u00d6\u0003J\t\u0010\u0007\u001a\u00020\bH\u00d6\u0001J\t\u0010\t\u001a\u00020\nH\u00d6\u0001\u00a8\u0006\u000b"}, d2 = {"Lar/jornadas/ui/ProgramaUi$SinDatos;", "Lar/jornadas/ui/ProgramaUi;", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "app_debug"})
    public static final class SinDatos implements ar.jornadas.ui.ProgramaUi {
        @org.jetbrains.annotations.NotNull()
        public static final ar.jornadas.ui.ProgramaUi.SinDatos INSTANCE = null;
        
        private SinDatos() {
            super();
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public java.lang.String toString() {
            return null;
        }
    }
}