
// TravellingListPage.jsx - Main Component
import { useEffect, useState } from "react";
import { travellingApi } from "../services/travellingApi";
import CreateTravelForm from "../components/CreateTravelForm";
import TravelCard from "../components/TravelCard";

export default function TravellingListPage() {
    const [items, setItems] = useState([]);
    const [loading, setLoading] = useState(true);

    const load = async () => {
        setLoading(true);
        try {
            const res = await travellingApi.getAll();
            setItems(res.data);
        } catch (error) {
            console.error("Failed to load travels:", error);
        } finally {
            setLoading(false);
        }
    };

    useEffect(() => { load(); }, []);

    return (
        <div style={styles.container}>
            <div style={styles.content}>
                <header style={styles.header}>
                    <h1 style={styles.title}>🌍 My Travel Log</h1>
                    <p style={styles.subtitle}>Keep track of your adventures around the world</p>
                </header>

                <CreateTravelForm onCreated={load} />

                {loading ? (
                    <div style={styles.loading}>
                        <div style={styles.spinner}></div>
                        <p>Loading your travels...</p>
                    </div>
                ) : items.length === 0 ? (
                    <div style={styles.emptyState}>
                        <div style={styles.emptyIcon}>✈️</div>
                        <p style={styles.emptyText}>No trips yet. Start adding your travels!</p>
                    </div>
                ) : (
                    <div style={styles.grid}>
                        {items.map(item => (
                            <TravelCard key={item.id} item={item} onUpdate={load} />
                        ))}
                    </div>
                )}
            </div>
        </div>
    );
}

const styles = {
    container: {
        minHeight: '100vh',
        background: 'linear-gradient(to bottom, #f0f9ff, #e0f2fe)',
        padding: '32px 24px',
        fontFamily: '-apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, sans-serif'
    },
    content: {
        maxWidth: '900px',
        margin: '0 auto'
    },
    header: {
        marginBottom: '32px',
        textAlign: 'center'
    },
    title: {
        fontSize: '42px',
        fontWeight: '700',
        margin: '0 0 8px 0',
        background: 'linear-gradient(135deg, #667eea 0%, #764ba2 100%)',
        WebkitBackgroundClip: 'text',
        WebkitTextFillColor: 'transparent',
        backgroundClip: 'text'
    },
    subtitle: {
        color: '#6b7280',
        fontSize: '16px',
        margin: 0
    },
    grid: {
        display: 'grid',
        gap: '16px'
    },
    loading: {
        textAlign: 'center',
        padding: '48px',
        color: '#6b7280'
    },
    spinner: {
        border: '3px solid #f3f4f6',
        borderTop: '3px solid #4f46e5',
        borderRadius: '50%',
        width: '40px',
        height: '40px',
        animation: 'spin 1s linear infinite',
        margin: '0 auto 16px'
    },
    emptyState: {
        background: 'white',
        padding: '48px',
        borderRadius: '12px',
        textAlign: 'center',
        boxShadow: '0 2px 8px rgba(0,0,0,0.08)'
    },
    emptyIcon: {
        fontSize: '48px',
        marginBottom: '16px'
    },
    emptyText: {
        fontSize: '16px',
        color: '#9ca3af',
        margin: 0
    }
};